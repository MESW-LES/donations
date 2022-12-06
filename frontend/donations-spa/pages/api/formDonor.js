export default function handlerDonor(req, res) {
    // Get data submitted in request's body.
    if (req.method == "POST") {
        const body = req?.body;

        // Optional logging to see the responses
        // in the command line where next.js app is running.
        //console.log('body: ', body)

        //console.log(!body.code);
        //console.log(!body.name);
        //console.log(!body.description);
        // Guard clause checks for first and last name,
        // and returns early if they are not found
        if (!body.taxnumber || !body.name || !body.phone || !body.password) {
            // Sends a HTTP bad request error code
            return res.status(400).json({ data: 'First or last name not found' })
        }

        // Found the name.
        // Sends a HTTP success code
        res.status(200).json({ data: `${body.taxnumber} ${body.name} ${body.phone} ${body.password}` })

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ taxnumber: body.taxnumber, name: body.name, phone: body.phone, password: body.password })
        };
        fetch('http://localhost:8080/registerdonor', requestOptions).then(
            response => response.json().then(
                data => console.log(data)
            )
        )

    }

}