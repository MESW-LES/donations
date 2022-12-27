
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
      fetch('http://localhost:8080/donations', requestOptions).then(
        response => response.json().then(
          data => res.status(200).json({code: 200, data: data})
        )
      )

    }

/*     if(req.method == "POST"){
        const body = req?.body;
        
    
        if (!body.title) {
            // Sends a HTTP bad request error code
            console.log("DEBUG S2");
            return res.status(401).json({ data: body })
          }else{
            if (!body.category) {
              // Sends a HTTP bad request error code
              return res.status(402).json({ data: 'Category not found' })
            }else{
              if (!body.description) {
                // Sends a HTTP bad request error code
                return res.status(403).json({ data: 'Description not found' })
              }
            }
          }
        
        const requestOptions = {
          method: 'POST',
          body: body
      };
        fetch('http://localhost:8080/donations', {
            method: 'POST',
            body: body
        }).then(
          response => response.json().then(
            data => {
                console.log("DEBUG S1");
                console.log(data);
                res.status(200).json({code: 200, data: `${body}` });
            }
          )
        )
  
      } */
    
  }
