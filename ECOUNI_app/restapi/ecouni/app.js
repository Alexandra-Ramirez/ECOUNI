var express = require("express");
var router=express.Router();
var bodyParser = require("body-parser");
var aplicacion = express();
var usuarios = require("./routes/rutasUsuarios");

router.get('/', function(request, response) {
response.status(200).json({"mensaje":"Nuestra primera app con node.js utilizando express"});
});

aplicacion.use(bodyParser.json({limit: '50mb'}));
aplicacion.use(bodyParser.urlencoded({limit: '50mb', extended: true}));
//incluismo el archivo en el que se almacena las rutas de cada entidad
aplicacion.use(router);
aplicacion.use(usuarios);
aplicacion.use("/ecouni/public",express.static(__dirname+'/public'));

aplicacion.listen(8080, function() {
console.log("Servidor iniciado");
});
