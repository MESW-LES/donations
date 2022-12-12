
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

export class CategoryService {
    constructor() {}

    getCategory() {
        /* return fetch('json/products.json', { headers: { 'Cache-Control': 'no-cache' } })
            .then((res) => res.json())
            .then((d) => d.data); */

            //res.status(200).json({code: 200, data: `${body.code} ${body.name} ${body.description}` })
  
            
            
            
            const requestOptions = {
              method: 'GET',
              headers: { 'Content-Type': 'application/json' },
          };
            fetch('http://localhost:8080/categories', requestOptions).then(
              response => response.json().then(
                data => console.log(data)
              )
            )




/*  return fetch('json/categories.json', { headers: { 'Cache-Control': 'no-cache' } })
            .then((res) => res.json())
            .then((d) => d.data);  */

    }

}
