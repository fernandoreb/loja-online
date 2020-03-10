/**
 * Controller de Produtos
 * 
 * redis utilizado para cache da lista de produtos
 */

const Status = require('http-status')
const ProductRepository = require('../repositories/productRepository');
const clientRedis = require('../config/redisConfig');

exports.findById = (request, response, next) => {

    ProductRepository.getById(request.params.id)
        .then((product) => {
            if (product)
                response.status(200).send(product);
            else
                response.status(Status.NOT_FOUND).send()
        }).catch(err => next(err));
}

exports.findAll = (request, response, next) => {

    clientRedis.get('allproducts', function (err, reply) {
        
        if (err){
            next(err)
        } 
        else if(reply){
            //console.log('redis');
            response.status(200).send(reply);
        }
        else{
            //console.log('db');
            ProductRepository.getAll()
            .then((product) => {
                if (product){
                    clientRedis.set('allproducts',JSON.stringify(product));
                    clientRedis.expire('allproducts',20);
                    //clientRedis.setex('allproducts',20,JSON.stringify(product));
                    response.status(200).send(product);
                }
                else
                    response.status(Status.NOT_FOUND).send()
            }).catch(err => next(err));
        }
    });
    
}

exports.create = (request, response, next) => {
    const p = request.body;

    ProductRepository.create(p)
        .then((product) => {
            clientRedis.del('allproducts', function (err, reply) {
                if (err){
                    console.log('Error to remove key allproducts on redis');
                }
            });
            response.status(Status.CREATED).send();
        }).catch(err => next(err));
}

exports.update = (request, response, next) => {
    const id = request.params.id
    const p = request.body;

    ProductRepository.update(id, p)
        .then((product) => {
            clientRedis.del('allproducts', function (err, reply) {
                if (err){
                    console.log('Error to remove key allproducts on redis');
                }
            });
            response.status(Status.CREATED).send(product);
        }).catch(err => response.status(500).send(err))

}

exports.delete = (request, response, next) => {
    const id = request.params.id;

    ProductRepository.getById(id)
        .then((product) => {
            if (product) {
                ProductRepository.delete(id)
                    .then(() => {
                        clientRedis.del('allproducts', function (err, reply) {
                            if (err){
                                console.log('Error to remove key allproducts on redis');
                            }
                        });
                        response.status(200).send();
                    }).catch((error) => next(error))
            }
            else
                response.status(Status.NOT_FOUND).send()
        }).catch(err => next(err));

}