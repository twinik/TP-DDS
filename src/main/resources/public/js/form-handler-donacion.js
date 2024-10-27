function handleFormSubmit(formId, submitButtonId) {
    const form = document.getElementById(formId);
    const submitButton = document.getElementById(submitButtonId);
    const heladeraError = document.getElementById('heladera-error');
    const heladeraId = document.getElementById('idHeladera');

    form.addEventListener('submit', function(event) {
        if (!heladeraId.value) {
            event.preventDefault();
            heladeraError.classList.remove('hidden');
            return;
        }

        heladeraError.classList.add('hidden');

        submitButton.textContent = 'Enviando...';
        submitButton.disabled = true;
    });
}