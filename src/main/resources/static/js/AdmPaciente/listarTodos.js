window.addEventListener('load', function () {
    const botonListar = document.getElementById('listar_todos');
    const botonModificar = document.getElementById('boton_modificar');
    const botonEliminar = document.getElementById('boton_eliminar');
    const tablaPacientes = document.getElementById('div_paciente_table');

    (function(){

         //con fetch invocamos a la API de paciente con el método GET
         //nos devolverá un JSON con una colección de pacientes
         const url = '/pacientes/listarTodos';
         const settings = {
           method: 'GET'
         }

         fetch(url,settings)
         .then(response => response.json())
         .then(data => {
         //recorremos la colección de pacientes del JSON
            for(pacientes of data){
               //por cada paciente armaremos una fila de la tabla
               const table = document.getElementById("pacienteTable");
               const pacienteRow =table.insertRow();
               let tr_id = 'tr_' + paciente.id;
               pacienteRow.id = tr_id;

               //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
               //dicho boton invocara a la funcion de java script eliminarPaciente que se encargará
               //de llamar a la API para eliminar un paciente
               let deleteButton = '<button' +
                                         ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                         ' type="button" onclick="eliminarPaciente('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                         Eliminar + '&times' +
                                  '</button>';

               //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
               //a la función de java script findBy que se encargará de buscar al paciente que queremos
               //modificar y mostrar los datos de la misma en un formulario.
               let updateButton = '<button' +
                                         ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                         ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                         Modificar +
                                         '</button>';

               //armamos cada columna de la fila
               paciente.innerHTML = '<td>' + updateButton + '</td>' +
                       '<td class=\"td_id\">' + paciente.id + '</td>' +
                       '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                       '<td class=\"td_apellido\">' + paciente.apellido + '</td>'+
                       '<td class=\"td_dni\">' + paciente.dni + '</td>'+
                       '<td class=\"td_domicilio\">' + paciente.domicilio + '</td>' +
                       '<td class=\"td_fechaDeAlta\">' + paciente.fechaDeAlta + '</td>'+
                       '<td>' + updateButtonButton + '</td>';
                       '<td>' + deleteButton + '</td>';

           };
}

       })