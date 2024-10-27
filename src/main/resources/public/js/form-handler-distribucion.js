function handleFormSubmit(formId, submitButtonId) {
    document.getElementById(formId).addEventListener('submit', function (event) {
        const submitButton = document.getElementById(submitButtonId);
        const heladeraOrigen = document.getElementById('idHeladeraOrigen').value;
        const heladeraDestino = document.getElementById('idHeladeraDestino').value;

        let errorMessage = document.getElementById('error-message');
        if (!errorMessage) {
            errorMessage = document.createElement('p');
            errorMessage.id = 'error-message';
            errorMessage.className = 'p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50';
            submitButton.parentElement.insertBefore(errorMessage, submitButton);
        }

        if (!heladeraOrigen || !heladeraDestino) {
            event.preventDefault();
            errorMessage.innerText = 'Por favor, seleccione una heladera de origen y una de destino.';

            submitButton.disabled = false;
            submitButton.innerHTML = 'Registrar donación';
            return;
        } else if (heladeraOrigen === heladeraDestino) {
            event.preventDefault();
            errorMessage.innerText = 'Las heladeras de origen y destino no pueden ser las mismas. Seleccione heladeras diferentes.';

            submitButton.disabled = false;
            submitButton.innerHTML = 'Registrar donación';
            return;
        }
        errorMessage.innerText = '';
        submitButton.disabled = true;
        submitButton.innerHTML = 'Enviando...';

    });
}
