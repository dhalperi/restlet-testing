restlet-testing
===============

Simple demonstration of the 1024 byte bug

Run the MasterApiServer app.

Submit a 1024-byte JSON query to the Echo resource as follows:

`time curl -i -XPOST localhost:8080/echo -H "Content-type: application/json" -d @./1024_bytes.json`

(the `time` is for getting the time of the execution).

```
time curl -i -XPOST localhost:8080/echo -H "Content-type: application/json" -d @./1024_bytes.json
HTTP/1.1 200 OK
Date: Fri, 15 Feb 2013 22:54:01 GMT
Accept-Ranges: bytes
Server: Restlet-Framework/2.1.1
Content-Length: 1024
Content-Type: application/json; charset=UTF-8

{  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "22_bytes" : "aaaa",}
curl -i -XPOST localhost:8080/echo -H "Content-type: application/json" -d   0.01s user 0.01s system 32% cpu 0.031 total
```

Note that this execution took about 0.031s total to run. In the RESTlet logs, the report is:
```
INFO: 2013-02-15  14:54:01	0:0:0:0:0:0:0:1	-	-	8080	POST	/echo	-	200	1024	1024	3	http://localhost:8080	curl/7.29.0	-
```
showing that it took about 3 ms to process.

Now try with 1025 bytes:

```
time curl -i -XPOST localhost:8080/echo -H "Content-type: application/json" -d @./1025_bytes.json
HTTP/1.1 200 OK
Date: Fri, 15 Feb 2013 22:55:51 GMT
Accept-Ranges: bytes
Server: Restlet-Framework/2.1.1
Content-Length: 1025
Content-Type: application/json; charset=UTF-8

{  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "fifty_bytes" : "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",  "23_bytes" : "aaaaa",}
curl -i -XPOST localhost:8080/echo -H "Content-type: application/json" -d   0.00s user 0.01s system 0% cpu 1.024 total
```

It took about 1.024s total! The logs say:

```
INFO: 2013-02-15  14:55:51	0:0:0:0:0:0:0:1	-	-	8080	POST	/echo	-	200	1025	1025	1001	http://localhost:8080	curl/7.29.0	-
```
with 1001 ms report processing time.
