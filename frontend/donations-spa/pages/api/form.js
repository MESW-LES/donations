export default function handler(req, res) {
    // Get data submitted in request's body.
    const body = req.body
  
    // Optional logging to see the responses
    // in the command line where next.js app is running.
    console.log('body: ', body)
  
    console.log(!body.category_code);
    console.log(!body.category_name);
    console.log(!body.category_description);
    // Guard clause checks for first and last name,
    // and returns early if they are not found
    if (!body.category_code || !body.category_name || !body.category_description) {
      // Sends a HTTP bad request error code
      return res.status(400).json({ data: 'First or last name not found' })
    }
  
    // Found the name.
    // Sends a HTTP success code
    res.status(200).json({ data: `${body.category_code} ${body.category_name} ${body.category_description}` })

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ code: body.category_code ,name: body.category_name, description: body.category_description })
  };
    fetch('http://localhost:8080/categories', requestOptions).then(
      response => response.json().then(
        data => console.log(data)
      )
    )
  }