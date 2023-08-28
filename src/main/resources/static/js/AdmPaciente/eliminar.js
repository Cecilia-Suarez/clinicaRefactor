function eliminarPaciente(id){
      const url = '/pacientes/'+ id;
      const settings = {
         method: 'DELETE'
      }
      fetch(url,settings)
      .then(response => response.json())
   })

//Se puede agregar un mensaje diciendo que el paciente fue eliminado con éxito?