package com.fizzbuzz.vroom.core.api.application;

/*
 * Copyright (c) 2014 Fizz Buzz LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fizzbuzz.vroom.core.api.VroomApiModule;
import com.fizzbuzz.vroom.core.api.resource.ResourceRegistry;
import com.fizzbuzz.vroom.core.api.resource.VroomResource;
import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import dagger.ObjectGraph;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class VroomApplication
    extends Application {

    public enum ExecutionContext {
        DEVELOPMENT,
        PRODUCTION
    }

    private static ExecutionContext mExecutionContext;
    private static String mRootUrl;
    private static String mServerUrl;
    private final Logger mLogger = LoggerFactory.getLogger(PackageLogger.TAG);

    static {
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
            mExecutionContext = ExecutionContext.PRODUCTION;
        else
            mExecutionContext = ExecutionContext.DEVELOPMENT;
    }

    private ObjectGraph mObjectGraph;

    public static String getRootUrl() {
        return mRootUrl;
    }

    public static void registerUrlRoot(final String rootUrl) {
        mRootUrl = rootUrl;
    }

    public static ExecutionContext getExecutionContext() {
        return mExecutionContext;
    }

    public static boolean inDevelopmentContext() {
        return mExecutionContext == ExecutionContext.DEVELOPMENT;
    }

    public static boolean inProductionContext() {
        return mExecutionContext == ExecutionContext.PRODUCTION;
    }

    public static String getServerUrl() {
        if (mServerUrl == null) {
            ModulesService modulesService = ModulesServiceFactory.getModulesService();
            mServerUrl = "http://" + modulesService.getVersionHostname(modulesService.getCurrentModule(),
                modulesService.getCurrentVersion());
        }

        return mServerUrl;
    }

    public static String buildUrlFromPath(final String resourcePath) {
        return getServerUrl() + mRootUrl + resourcePath;
    }

    @Override
    public Restlet createInboundRoot() {
        Restlet result = null;
        try {
            Router router = new Router(getContext());

            for (Map.Entry<Class<? extends VroomResource>, String> entry : ResourceRegistry.getPathTemplates()
                .entrySet()) {
                attach(router, entry.getValue(), entry.getKey());
            }

            result = router;
        } catch (RuntimeException e) {
            mLogger.error("VroomApplication.createInboundRoot: exception caught:", e);
            throw e;
        }

        return result;
    }

    @Override
    public void handle(final Request request, final Response response) {
        mLogger.info("request received: {}", request);
        // note: trace-level messages are not shown by default; change log level to FINEST in logging.properties
        // to see them
        mLogger.trace("request headers: {}", request.getAttributes());
        super.handle(request, response);
        mLogger.trace("response headers: {}", response.getAttributes());


    }

    @Override
    public synchronized void start() throws Exception {
        super.start();

        // request "strict" content negotiation from Restlet
        getConnegService().setStrict(true);

        // initialize the dagger object graph
        mObjectGraph = ObjectGraph.create(getModules().toArray());
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    protected void attach(Router router, String pathTemplate, java.lang.Class<? extends org.restlet.resource
        .ServerResource> target) {
        // note: trace-level messages are not shown by default; change log level to FINEST in logging.properties
        // to see them
        mLogger.trace("attaching path template {} to {}", pathTemplate, target);
        router.attach(pathTemplate, target);
    }

    /**
     * Returns the list of dagger modules to be included in this Detector's object graph. Subclasses that override this
     * method should add to the list returned by super.getModules().
     *
     * @return the list of modules
     */
    protected List<Object> getModules() {
        List<Object> result = new ArrayList<>();
        result.add(new VroomApiModule());
        return result;
    }
}

