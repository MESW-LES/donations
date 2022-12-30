export default function handler(req, res) {
    // Get data submitted in request's body.
    if (req.method == "POST") {
        const body = req?.body;

        if (!body.taxnumber) {
            // Sends a HTTP bad request error code
            return res.status(401).json({ data: 'Tax Number not found' })
        } else {
            if (!body.name) {
                // Sends a HTTP bad request error code
                return res.status(402).json({ data: 'Name not found' })
            }
        }

        // Found the name.
        // Sends a HTTP success code
        res.status(200).json({ code: 200, data: `${body.taxnumber}` })

        const requestRegister = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: body.name, taxnumber: body.taxnumber, password: body.password })
        };
        fetch('http://localhost:8080/donors', requestRegister).then(
            response => response.json().then(
                data => console.log(data)
            )
        )

    }

}