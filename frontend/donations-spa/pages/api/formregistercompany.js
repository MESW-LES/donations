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
            } else {
                if (!body.description) {
                    // Sends a HTTP bad request error code
                    return res.status(403).json({ data: 'Description not found' })
                }
            }
        }

        // Found the name.
        // Sends a HTTP success code
        res.status(200).json({ code: 200, data: `${body.taxnumber}` })

        const requestRegister = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ company: { name: body.name, description: body.description, taxnumber: body.taxnumber, phone: body.phone }, password: body.password, category: body.category })
        };
        fetch('http://localhost:8080/donees', requestRegister).then(
            response => response.json().then(
                data => console.log(data)
            )
        )

    }

}