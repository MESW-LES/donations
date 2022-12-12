
export default function handler(req, res) {
    // Get data submitted in request's body.
    if(req.method == "GET"){
      const body = req?.body;
     
      // Found the name.
      // Sends a HTTP success code
      //res.status(200).json({code: 200, data: `hey` })
  
      const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
      fetch('http://localhost:8080/categories', requestOptions).then(
        response => response.json().then(
          data => res.status(200).json({code: 200, data: data})
        )
      )

    }
    
  }

