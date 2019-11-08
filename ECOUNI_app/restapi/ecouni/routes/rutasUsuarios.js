var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutara las sentencias sql
var usuariosModel = require('../models/usuarios');
var historialModel = require('../models/historial');
var fs = require('fs');
var path = require('path');

//Login
router.post('/ecouni/usuario/login',function(req,res){
	var codigo = req.body.codigo;
	var pass = req.body.pass;
	
	//console.log(codigo);
	//console.log(pass);

	usuariosModel.getUsuarioByLogin(codigo,pass,function(error,datos){
		if(error){
			res.status(500).json({"mensaje":"Error en login"});
		}else if(datos && datos.length > 0 && datos[0].length>0){
			//datos.mensaje = "Acceso"
			
			var result = datos[0][0];
			result.mensaje = "Acceso"
			res.status(200).json(result);
		}else{
			res.status(500).json({"mensaje":"Codigo o pass no validos"});
		}
	});
});

//Modificar usuario
router.post('/ecouni/usuario/edit',function(req,res){
	var usuarioData={
		codigo : req.body.codigo,
		nombre : req.body.nombre,
		facultad : req.body.facultad,
		email: req.body.email,
		sobre_mi: req.body.sobre_mi,
		celular: req.body.celular,
		cumple: req.body.cumple
	};
	usuariosModel.updatePerfil(usuarioData,function(error,datos){
		//Si el usuario se ha actualizado correctamente mostramos un mensaje
		if(error){
			res.status(500).json({"mensaje":"Error"});
		}else if(datos && datos.length > 0 && datos[0].length>0){
			//datos.mensaje="Actualizado"
			var result = datos[0][0];
			result.mensaje = "Guardado";
			res.status(200).json(result);
		}else{
			res.status(500).json({"mensaje":"Error"});
		}
	});
});

//Modificar usuario foto
router.post('/ecouni/usuario/edit_foto',function(req,res){
	var codigo = req.body.codigo;
	var base64Thumbnail = req.body.foto;

	var path_foto = "";
	if(base64Thumbnail!=null && base64Thumbnail.length>0){
		path_foto = codigo+"_"+Date.now()+'.jpeg';

		//var path_foto = req.query.path_foto;
		fs.writeFile(path.join(__dirname, '../public/images/'+path_foto),new Buffer(base64Thumbnail,"base64"), function(error1){
			if(error1){	
				res.status(500).json({"mensaje":"Error en guardar miniatura"});
			}else{
				usuariosModel.updatePerfilFoto(codigo,path_foto,function(error,datos){
					//Si el usuario se ha actualizado correctamente mostramos un mensaje
					if(error){
						res.status(500).json({"mensaje":"Error"});
					}else if(datos && datos.length > 0 && datos[0].length>0){
						var result = datos[0][0];
						result.mensaje = "Guardado";
						res.status(200).json(result);
					}else{
						res.status(500).json({"mensaje":"Error en actualizar"});
					}
				});
			}
		});
	}else{
		usuariosModel.updatePerfilFoto(codigo,path_foto,function(error,datos){
			//Si el usuario se ha actualizado correctamente mostramos un mensaje
			if(error){
				res.status(500).json({"mensaje":"Error"});
			}else if(datos && datos.length > 0 && datos[0].length>0){
				var result = datos[0][0];
				result.mensaje = "Eliminado";
				res.status(200).json(result);
			}else{
				res.status(500).json({"mensaje":"Error en actualizar"});
			}
		});
	}
	
});

//Top 10
router.get('/ecouni/usuario/ranking',function(req,res){
	usuariosModel.getTop10(function(error,datos){
		if(error){
			res.status(500).json({"mensaje":"Error"});
		}else if(datos && datos.length > 0 ){
			var result = datos[0];
			result.mensaje = "top10";
			res.status(200).json(result);
		}else{
			res.status(500).json({"mensaje":"Error en consulta"});
		}
	});
});

//Reciclar
router.post('/ecouni/usuario/reciclar',function(req,res){
	var codigo = req.body.codigo;
	var cantidad = req.body.cantidad;
	var fecha = req.body.fecha;
	
	usuariosModel.reciclar(codigo,cantidad,fecha,function(error,datos){
		if(error){
			res.status(500).json({"mensaje":"Error"});
		}else if(datos && datos.length > 0 && datos[0].length>0){
			//datos.mensaje = "Reciclado"
			var result = datos[0][0];
			result.mensaje = "Reciclado";
			res.status(200).json(result);
		}else{
			res.status(500).json({"mensaje":"Error en consulta"});
		}
	});
});

//Historial
router.get('/ecouni/usuario/historial',function(req,res){
	var codigo = req.query.codigo;
	var year = req.query.year;
	var month = req.query.month;
	historialModel.getHistorial(codigo,year,month,function(error,datos){
		if(error){
			res.status(500).json({"mensaje":"Error"});
		}else if(datos && datos.length > 0 ){
			//datos.mensaje="Exito"
			var result = datos[0];
			result.mensaje = "historial";
			res.status(200).json(result);
		}else{
			res.status(500).json({"mensaje":"Error en consulta"});
		}
	});
});

module.exports = router;
