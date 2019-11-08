var conn=require('./connection');
var mysql=require('mysql');

//Se crea la conexion a la bd
connection=mysql.createConnection(conn);

var historial = {};

//Obtenemos un usuario por su codigo y pass
historial.getHistorial = function(codigo,year,month,callback){
	if(connection){
		var sql='call rec_historial('+connection.escape(codigo)
		+','+connection.escape(year)
		+','+connection.escape(month)+")";
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

module.exports = historial;
