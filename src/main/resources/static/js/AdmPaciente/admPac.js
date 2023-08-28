window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos

    const botonBuscar= document.getElementById('boton_buscar');

    const formBusqueda = document.getElementById('barra_busqueda');


    /* ---- BUSCAR ----- */
    botonBuscar.addEventListener('click', function (){
      formBusqueda.style.display= 'block';
      formulario.style.display= 'none';
    })

    formBusqueda.addEventListener('submit', function(event){
       const url = '/pacientes'+"/"+id;

       const settings = {
         method: 'GET'
       }

        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('paciente_id').value = paciente.id;
            document.querySelector('nombre').value = paciente.nombre;
            document.querySelector('apellido').value = paciente.apellido;
            document.querySelector('dni').value = paciente.dni;
            document.querySelector('domicilio').value = paciente.domicilio;
            document.querySelector('fechaDeAlta').value = paciente.fechaDeAlta;

            //el formulario por default está oculto y al editar se habilita

            tablaPacientes.style.display = 'block';

        }).catch(error => {
            alert("Error: " + error);
        })
    })


   /* ---- MODIFICAR ---- */
   botonModificar.addEventListener('click', function (){
         tablaPacientes.style.display= 'none';
         formulario.style.display = 'block';
   })

   formulario.addEventListener('submit', function (event) {
           let peliculaId = document.querySelector('paciente_id').value;

           //creamos un JSON que tendrá los datos del paciente
           const formData = {
               id: document.querySelector('paciente_id').value,
               nombre: document.querySelector('nombre').value,
               apellido: document.querySelector('apellido').value,
               dni: document.querySelector('dni').value,
               domicilio: document.querySelector('domicilio').value,
               fechaDeAlta: document.querySelector('fechaAlta').value,
           };

           //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
           //al paciente que enviaremos en formato JSON
           const url = '/pacientes/update';
           const settings = {
               method: 'PUT',
               headers: {
                   'Content-Type': 'application/json',
               },
               body: JSON.stringify(formData)
           }
             fetch(url,settings)
             .then(response => response.json())
       })


   /* ---- LISTAR TODOS ---- */


       })








    /*(function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/peliculaList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();*/
});