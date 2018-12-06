# JavaEE | RESTful example
Simple example Angular + Java RESTful + WildFly 8.2

### Part of standalone.xml (WildFly)

<subsystem xmlns="urn:jboss:domain:undertow:1.2">
    <buffer-cache name="default"/>
    <server name="default-server">
        <http-listener name="default" socket-binding="http"/>
        <host name="default-host" alias="localhost">
            <location name="/" handler="welcome-content"/>
            <filter-ref name="server-header"/>
            <filter-ref name="x-powered-by-header"/>
            <filter-ref name="Access-Control-Allow-Origin"/>
            <filter-ref name="Access-Control-Allow-Headers"/>
        </host>
    </server>
    <servlet-container name="default">
        <jsp-config/>
        <websockets/>
    </servlet-container>
    <handlers>
        <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
    </handlers>
    <filters>
        <response-header name="server-header" header-name="Server" header-value="WildFly/8"/>
        <response-header name="x-powered-by-header" header-name="X-Powered-By" header-value="Undertow/1"/>
        <response-header name="Access-Control-Allow-Origin" header-name="Access-Control-Allow-Origin" header-value="*"/>
        <response-header name="Access-Control-Allow-Headers" header-name="Access-Control-Allow-Headers" header-value="accept, authorization, content-type, x-requested-with"/>
    </filters>
</subsystem>

### Part of Angular

public testSend() {
this.http.post('http://localhost:8080/RESTful/service/post', {body: 'qwerty'}, this.httpOptions)
  .subscribe(
    value => {
      console.log('POST call successful value returned in body', value);
    },
    error => {
      console.log('POST call in error', error);
    },
    () => {
      console.log('The POST observable is now completed.');
    });
}
