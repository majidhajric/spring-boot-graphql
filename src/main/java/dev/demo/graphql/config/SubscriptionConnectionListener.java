package dev.demo.graphql.config;

import graphql.kickstart.execution.subscriptions.SubscriptionSession;
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener;
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConnectionListener implements ApolloSubscriptionConnectionListener {

    @Override
    public void onConnect(SubscriptionSession session, OperationMessage message) {
        ApolloSubscriptionConnectionListener.super.onConnect(session, message);
    }

    @Override
    public void onStart(SubscriptionSession session, OperationMessage message) {
        ApolloSubscriptionConnectionListener.super.onStart(session, message);
    }
}
