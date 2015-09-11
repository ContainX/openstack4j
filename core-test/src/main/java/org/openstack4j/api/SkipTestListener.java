package org.openstack4j.api;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.openstack4j.core.transport.internal.HttpExecutor;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

/**
 * A TestNG listener checking test methods for the presence of {@code @SkipTest} 
 * annotation
 * 
 * <p>If annotation is present on test method and current HTTP connector matches
 * the HTTP connector specified by {@code @SkipTest}, the listener will skip the test
 * by throwing TestNG {@code SkipException}.</p>
 * 
 * @author Bruno Semperlotti
 */
public class SkipTestListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iim, ITestResult itr) {

        Method method = iim.getTestMethod().getConstructorOrMethod().getMethod();

        // Check if method is annotated with @SkipTest
        if (method.isAnnotationPresent(SkipTest.class)) {
            SkipTest skipAnnotation = method.getAnnotation(SkipTest.class);
            String httpExecutorName = HttpExecutor.create().getExecutorName();

            // Check if specified connector matches current used connector
            // Annotation connector parameter can be a regex like ".*"
            if (httpExecutorName.matches(skipAnnotation.connector())) {

                StringBuilder message = new StringBuilder();
                message.append(String.format(
                        "Skip test %s for connector %s",
                        method.getName(), httpExecutorName));

                if (skipAnnotation.issue() > 0) {
                    message.append(String.format(
                            " due to issue %s",
                            skipAnnotation.issue()));
                }
                if (!skipAnnotation.description().isEmpty()) {
                    message.append(String.format(
                            ": %s",
                            skipAnnotation.description()));
                }

                // Skip Test
                Logger.getLogger(getClass().getName()).warning(message.toString());
                throw new SkipException(message.toString());
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iim, ITestResult itr) {
        // nothing to do
    }

}
