# RabbitMQ JMS Client Compliance Test Suite 

## Overview

This a test harness for exercising the [RabbitMQ JMS client](https://github.com/rabbitmq/rabbitmq-jms-client) against the JMS 1.1 specification.
It uses the [Java Message Service Compliance Test Suite](http://jmscts.sourceforge.net/).

The [JMS Client Reference webpage](https://www.rabbitmq.com/jms-client-compliance.html)
lists the supported interfaces.

## Running the Compliance Test Suite

### Running the test suite against an externally provided broker

You need a RabbitMQ node with the default options and
with the `rabbitmq_jms_topic_exchange` plugin enabled.
You can then run the test suite with Maven:

    ./mvnw clean verify -P '!setup-test-node'
    
### Running the test suite against a build-started broker

See [CONTRIBUTING.md](./CONTRIBUTING.md) for an overview of the development process.

You can then run the test suite with Maven (the build process will and stop a broker
node with the appropriate configuration):

    ./mvnw clean verify
    
### Reading the results

Results can be found in:

 * `target/jmscts-report/html/coverage.html`
 * `target/jmscts-selector-report/html/coverage.html`
 * `target/jmscts-browse-report/html/coverage.html`

## License and Copyright

(c) 2007-2020 VMware, Inc. or its affiliates.

This package, the RabbitMQ JMS client CTS, is licensed the Mozilla Public License
2.0 ("MPL").

See [LICENSE](./LICENSE).
