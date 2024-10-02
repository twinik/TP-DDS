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