/**
 * Configurações do redis
 */

var redis = require('redis');

//desenvolvimento
var client = redis.createClient({
  host: "localhost",
  port: 6379
}
);

//docker-compose
/*var client = redis.createClient({
    host: "redis",
    port: 6379
  }
);*/

module.exports = client;