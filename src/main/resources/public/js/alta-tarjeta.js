document.getElementById('altaTarjetaForm').addEventListener('submit', function(event) {
    const codigoTarjeta = document.getElementById('codigoTarjeta').value;
    if (codigoTarjeta.length !== 11) {
        alert('El código debe tener exactamente 11 caracteres.');
        event.preventDefault();
    } else {
        const confirmacion = confirm(`¿Estás seguro que quieres enviar este código: ${codigoTarjeta}?`);
        if (!confirmacion) {
            event.preventDefault();
        }
    }
});