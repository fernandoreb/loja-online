/**
 * Configurações do redis
 */

var redis = require('redis');

//desenvolvimento
/*var client = redis.createClient({
  host: "localhost",
  port: 6379
}
);*/

//export REDIS_HOST=192.168.0.27
//export REDIS_PORT=32625
var redisHost = process.env.REDIS_HOST;
var redisPort = process.env.REDIS_PORT;
//env REDIS_HOST
//env REDIS_PORT

var client = redis.createClient({
    host: ""+redisHost+"",
    port: redisPort
  }
);

module.exports = client;