var conn=require('./connection');
var mysql=require('mysql');

//Se crea la conexion a la bd
connection=mysql.createConnection(conn);

var usuarios = {};

//Obtenemos el top 10 del ranking
usuarios.getTop10 = function(callback){
	if(connection){
		connection.query('call top10()', function(error,rows){
			if(error){
				//console.log(error.stack);
				callback(error);
			}else{
				callback(null, rows);
			}
		});
	}
}

//Obtenemos un usuario por su codigo y pass
usuarios.getUsuarioByLogin = function(codigo,pass,callback){
	if(connection){
		var sql='call login('+connection.escape(codigo)+','+connection.escape(pass)+")";
		connection.query(sql, function(error,row){
			if(error){
				//console.log(error.stack);
				callback(error);
			}else{
				callback(null,row);
			}
		});
	}
}

//Actualizar un usuario
usuarios.updatePerfil = function(usuarioData, callback){
	if(connection){
		var sql = 'call edit_perfil('+ connection.escape(usuarioData.codigo)
		+','+ connection.escape(usuarioData.nombre)
		+','+ connection.escape(usuarioData.facultad)
		+','+ connection.escape(usuarioData.email)
		+','+ connection.escape(usuarioData.sobre_mi)
		+','+ connection.escape(usuarioData.celular)
		+','+ connection.escape(usuarioData.cumple)+')';
		connection.query(sql, function(error, result) {
			if(error){
				//console.log(error.stack);
				callback(error);
			}else{
				callback(null,result);
			}
		});
	}
}

//Actualizar un usuario
usuarios.updatePerfilFoto = function(codigo,path_foto, callback){
	if(connection){
		var sql = 'call edit_foto('+ connection.escape(codigo)
		+','+ connection.escape(path_foto)+')';
		connection.query(sql, function(error, result) {
			if(error){
				//console.log(error.stack);
				callback(error);
			}else{
				callback(null,result);
			}
		});
	}
}


//Actualizar un usuario
usuarios.reciclar = function(codigo,cantidad,fecha,callback){
	if(connection){
		var sql = 'call reciclar('+ connection.escape(codigo)
		+','+connection.escape(cantidad)
		+','+connection.escape(fecha)+')';
		connection.query(sql, function(error, result) {
			if(error){
				//console.log(error.stack);
				callback(error);
			}else{
				callback(null,result);
			}
		});
	}
}

module.exports = usuarios;
