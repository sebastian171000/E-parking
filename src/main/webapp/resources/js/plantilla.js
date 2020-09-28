function paginaActiva(bodyClass,n){
	var item = document.querySelector("body");
	var hasClase = item.classList.contains(bodyClass);
	
	if(hasClase){
		document.querySelector(`.${bodyClass} .ui-menu-list li:nth-child(${n}) a`).classList.add('active');
		document.querySelector(`.${bodyClass} .botones-pendiente-historial a:nth-child(1)`).classList.add('active2');
	}
}
//PARA HOME.XHTML
paginaActiva('home',1);
//PARA VEHICULO.XHTML
paginaActiva('vehiculo',2);
//PARA RESERVA.XHTML
paginaActiva('reserva',3);
//PARA PERFIL.XHTML
paginaActiva('perfil',4);

//PARA HOMEADMIN.XHTML
paginaActiva('homeAdmin',1);
//PARA RESERVAADMIN.XHTML
paginaActiva('reservaAdmin',2);
//PARA estacionamientoAdmin.XHTML
paginaActiva('estacionamientoAdmin',3);
//PARA PERFILADMIN.XHTML
paginaActiva('perfilAdmin',4);

function historialActivo(bodyClass){
	var item = document.querySelector("body");
	var hasClase = item.classList.contains(bodyClass);
	
	if(hasClase){
		document.querySelector(`.${bodyClass} .botones-pendiente-historial a:nth-child(2)`).classList.add('active2');
	}
}
historialActivo('reservaHistorial');
historialActivo('reservaHistorialAdmin');

