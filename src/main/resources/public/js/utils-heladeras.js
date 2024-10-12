async function fetchHeladeras() {
    try {
        const response = await fetch('/heladeras/mapa');
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const heladerasArray = await response.json();
        return heladerasArray;
        //
        // console.log(heladerasArray)
        //
        // return heladerasArray.map(h => {
        //     if (h.disabled) {
        //         return {...h, extraContent: inhabilitadaContent}
        //     } else {
        //         return h;
        //     }
        // });
    } catch (error) {
        console.error('Failed to fetch heladeras:', error);
    }
}

async function fetchHeladerasDonaciones() {
    try {
        const response = await fetch('/heladeras/donar');
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }

        const heladerasArray = await response.json();
        return heladerasArray;
        //
        // console.log(heladerasArray)
        //
        // return heladerasArray.map(h => {
        //     if (h.disabled) {
        //         return {...h, extraContent: inhabilitadaContent}
        //     } else {
        //         return h;
        //     }
        // });
    } catch (error) {
        console.error('Failed to fetch heladeras:', error);
    }
}

async function fetchDonaciones(provincia, localidad) {
    try {
        const response = await fetch(`/heladeras/recibir-puntos?provincia=${provincia}&localidad=${localidad}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        const donaciones = await response.json();
        console.log('Donaciones:', donaciones);
        return donaciones;
    } catch (error) {
        console.error('Failed to fetch donaciones:', error);
    }
}

async function fetchDonacionesCercanas(latitud, longitud, radio) {
    try {
        const response = await fetch(`/colaborar/recibir-puntos-colocacion?latitud=${latitud}&longitud=${longitud}&radio=${radio}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        const puntos_colocacion = await response.json();
        console.log('Puntos Colocacion:', puntos_colocacion);
        return puntos_colocacion;
    } catch (error) {
        console.error('Failed to fetch puntos colocacion:', error);
    }
}