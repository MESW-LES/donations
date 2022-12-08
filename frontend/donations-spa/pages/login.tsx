import { Card } from "primereact/card";

const Login = () => {
  const backImage = `.back-image{
        background: url(https://www.owensboroparent.com/wp-content/uploads/2017/01/GiftofGiving.jpg);
    }`;

  return (
    <>
      <div
        className="back-image"
        style={{
          width: "100%",
          height: "100vh",
          backgroundSize: "1370px",
          backgroundRepeat: "no-repeat",
        }}
      >
        <div className="flex justify-end pr-20 pt-44">
          <Card className="bg-white w-4 h-96">
            <div className="grid grid-cols-3">
              <div className="w-3"></div>
              <div className="w-6">
                <h1 className="text-black flex justify-center">Login</h1>
              </div>
              <div className="w-3"></div>
            </div>
          </Card>
        </div>
      </div>
      <style>{backImage}</style>
    </>
  );
};

export default Login;
