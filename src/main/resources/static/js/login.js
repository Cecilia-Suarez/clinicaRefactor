/*window.addEventListener('load', function () {
    /* ---------------------- obtenemos variables globales ----------------------
    const form = document.forms[0];
    const email = document.querySelector("#inputEmail");
    const password = document.querySelector("#inputPassword");
    const url = 'https://http://localhost:8080//v1'; /*cambiar*/




    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    /*form.addEventListener('submit', (e) => {
        e.preventDefault();

        const playload = {
            email: email.value,
            password: password.value
        };

        const settings = {
            method: "POST",
            body: JSON.stringify(playload),
            headers: {
                'Content-Type': 'application/json'
            }
        };

        realizarLogin(settings);
        form.reset();
    });
*/

    /* -------------------------------------------------------------------------- */
    /*                     FUNCIÓN 2: Realizar el login [POST]                    */
    /* -------------------------------------------------------------------------- */
   /* function realizarLogin(settings) {
        console.log("Lanzando la consulta a la API");

        fetch(`${url}/users/login`, settings)
            .then(response => {
                console.log(response);
                if (response.ok != true) {
                    alert("Algún de los datos es incorrecto");
                }
                return response.json();
            })
            .then(data => {
                console.log("Promesa cumplida");
                console.log(data);
                if (data.jwt) {
                    localStorage.setItem("jwt", JSON.stringify(data.jwt));
                    location.replace("./mis-tareas.html");
                }
            })
            .catch(error => {
                console.log("Promesa rechazada");
                console.log(error);
            })
    };
});
 */