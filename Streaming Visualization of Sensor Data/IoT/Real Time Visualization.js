var http = require('http');
var fs = require('fs');
var port = 8686;
var i=0;


function randomInt(low, high) {
	return Math.floor(Math.random() * (high - low) + low);
}

// 404 response
function send404Response(response){
	response.writeHead(404,{"Content-Type": "text/plain" });
	response.write("Error 404: Page not found");
	response.end();
}

// handle the user request..

http.createServer(function(req, res) {
	console.log('New incoming client request for ' + req.url);
	res.writeHeader(200, {
		'Content-Type' : 'application/json'
	});
	switch (req.url) {
	case '/temperatureData':
		var mysql=require('mysql');
		var connection=mysql.createConnection({
			host:'localhost',
			user:'root',
			password:'root',
			database:'feedback',
			port:3306	
		});
		var query=connection.query(
				'SELECT * FROM DEMO2',function(err,result,fields){
					if(err) throw err;
					res.write('{"value" :' + result[i].tempvalue + '}');					
					console.log('result:', result[i].tempvalue);
					i++;
					//console.log(result[0].tempvalue)
					res.end();
					connection.end();
				});
		
		//connection.end();	
		//res.write('{"value" :' + randomInt(1, 40) + '}');
	
		//connection.end();
		break;	
	case '/temperature':	
		res.writeHead(200, 'text/html');
		var fileStream = fs.createReadStream('index.html');
		fileStream.pipe(res);
		break;	
	default:
	      send404Response(res);		
	}
}).listen(port);
console.log('Server listening on http://localhost:' + port);