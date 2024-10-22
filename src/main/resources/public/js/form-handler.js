function handleFormSubmit(formId, submitButtonId) {
    document.getElementById(formId).addEventListener('submit', function (event) {
        const submitButton = document.getElementById(submitButtonId);

        // Deshabilitar el botón inmediatamente después del envío
        submitButton.disabled = true;

        // Opcional: Cambiar el texto del botón
        submitButton.innerHTML = 'Enviando...';

        // console.log('hola')

        // Continuar con el envío del formulario
    });
}
