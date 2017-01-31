# RabbitMQ JMS Client Compliance Test Suite 

## Overview

This a test harness for exercising the RabbitMQ JMS Client against the JMS 1.1 specification.
It uses the [Java Message Service Compliance Test Suite](http://jmscts.sourceforge.net/).

The [JMS Client Reference webpage](http://www.rabbitmq.com/jms-client-compliance.html)
lists the supported interfaces.

## Running the Compliance Test Suite

### Running the test suite against an externally provided broker

You need a RabbitMQ node with the default options and
with the `rabbitmq_jms_topic_exchange` plugin enabled.
You can then run the test suite with Maven:

    mvn clean verify -P '!setup-test-node'
    
### Running the test suite against a build-started broker

See [CONTRIBUTING.md](./CONTRIBUTING.md) for an overview of the development process.

You can then run the test suite with Maven (the build process will and stop a broker
node with the appropriate configuration):

    mvn clean verify
    
### Reading the results

Results can be found in:

 * `target/jmscts-report/html/coverage.html`
 * `target/jmscts-selector-report/html/coverage.html`
 * `target/jmscts-browse-report/html/coverage.html`

## License and Copyright

(c) Pivotal Software, Inc., 2007-2017.

This package, the RabbitMQ JMS client library, is double-licensed
under the Apache License version 2 ("ASL") and the Mozilla Public License
1.1 ("MPL").

See [LICENSE](./LICENSE).
