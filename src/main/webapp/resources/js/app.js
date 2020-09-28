const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const button = document.getElementById('formulario:input_registro');


const expresiones = {
	usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	password: /^.{4,12}$/, // 4 a 12 digitos.
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	telefono: /^\d{9}$/, // 9 numeros.
	dni: /^\d{8}$/ // 8 numeros.
	
}
const campos = {
	usuario: false,
	nombre: false,
	apellido: false,
	password: false,
	correo: false,
	telefono: false,
}
button.disabled = true;
const validarFormulario = (e) => {
	switch (e.target.name) {
		case "formulario:input_usuario":
			validarCampo(expresiones.usuario, e.target, 'usuario');
		break;
		case "formulario:input_nombre":
			validarCampo(expresiones.nombre, e.target, 'nombre');
		break;
		case "formulario:input_password":
			validarCampo(expresiones.password, e.target, 'password');
			validarPassword2();
		break;
		case "formulario:input_correo":
			validarCampo(expresiones.correo, e.target, 'correo');
		break;
		case "formulario:input_telefono":
			validarCampo(expresiones.telefono, e.target, 'telefono');
		break;
		case "formulario:input_apellido":
			validarCampo(expresiones.nombre, e.target, 'apellido');
		break;
		
		case "formulario:input_password2":
			validarPassword2();
		break;
		
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
				if(campos.usuario && campos.password && campos.correo && campos.telefono && campos.nombre && campos.apellido){
					button.disabled = false;
				}else{
					button.disabled = true;

				}
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
		if(campos.usuario && campos.password && campos.correo && campos.telefono && campos.nombre && campos.apellido){
					button.disabled = false;
				}else{
					button.disabled = true;

				}
	}
}

const validarPassword2 = () => {
	const inputPassword1 = document.getElementById('formulario:input_password');
	const inputPassword2 = document.getElementById('formulario:input_password2');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__password2 .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos['password'] = false;
		if(campos.usuario && campos.password && campos.correo && campos.telefono && campos.nombre && campos.apellido){
					button.disabled = false;
				}else{
					button.disabled = true;

		}
	} else {
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__password2 .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos['password'] = true;
		if(campos.usuario && campos.password && campos.correo && campos.telefono && campos.nombre && campos.apellido){
					button.disabled = false;
				}else{
					button.disabled = true;

				}
	}
}

inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

button.addEventListener('click', () => {
	console.log('click');

	
	if(campos.usuario && campos.password && campos.correo && campos.telefono && campos.nombre 
	&& campos.apellido){
		formulario.reset();
		document.getElementById('formulario__mensaje').classList.remove('formulario__mensaje-activo');
		document.getElementById('formulario__mensaje-exito').classList.add('formulario__mensaje-exito-activo');
		setTimeout(() => {
			document.getElementById('formulario__mensaje-exito').classList.remove('formulario__mensaje-exito-activo');
		}, 5000);

		document.querySelectorAll('.formulario__grupo-correcto').forEach((icono) => {
			icono.classList.remove('formulario__grupo-correcto');
		});
	} else {
		document.getElementById('formulario__mensaje').classList.add('formulario__mensaje-activo');
	}
});