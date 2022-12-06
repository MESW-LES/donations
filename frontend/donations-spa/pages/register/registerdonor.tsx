import axios from "axios";
import { Password } from "primereact";
import React from "react";
import { useState } from "react";

function RegisterDonor() {
    const [taxnumber, setTaxnumber] = useState("");
    const [name, setName] = useState("");
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");


    const handleSignUp = async (event: any) => {

        //não atualiza a pagina
        event.preventDefault();

        const formValues = { taxnumber, name, phone, password };

        //console.log(formValues);

        try {
            const { data } = await axios({
                url: "/api/formDonor",
                method: "POST",
                data: formValues,
            });

            console.log("Response: ", data);
        } catch (error) {
            console.log("error:", error);
        }
    };

    return (
        <>
            <div className="bg-gray-700 mt-10 sm:mt-0 pt-10">
                <div className="mt-5 md:col-span-2 md:mt-0 ">
                    <form action="/api/form" method="POST">
                        <div className="overflow-hidden shadow sm:rounded-md py-4 space-y-4 px-24">
                            <div className="border border-transparent rounded-md bg-white py-5 sm:p-6">
                                <h2 className="text-lg font-bold text-center mb-4">Sign up</h2>
                                <div className="grid grid-cols-6 gap-6">
                                    <div className="col-span-12">
                                        <label
                                            htmlFor="taxnumber "
                                            className="block text-sm font-medium text-gray-700"
                                        >
                                            NIF
                                        </label>
                                        <input
                                            required
                                            minLength={1}
                                            maxLength={4}
                                            type="text"
                                            name="taxnumber "
                                            id="taxnumber "
                                            value={taxnumber}
                                            //autoComplete="given-name"
                                            onChange={({ target }) => setTaxnumber(target?.value)}
                                            className="mt-1 block w-8/12 rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                        />
                                    </div>
                                    <div className="col-span-12">
                                        <label
                                            htmlFor="name"
                                            className="block text-sm font-medium text-gray-700"
                                        >
                                            Name
                                        </label>
                                        <input
                                            required
                                            minLength={1}
                                            maxLength={50}
                                            type="text"
                                            name="name"
                                            id="name"
                                            value={name}
                                            onChange={({ target }) => setName(target?.value)}
                                            //autoComplete="given-name"
                                            className="mt-1 block w-8/12 rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                        />
                                    </div>

                                    <div className="col-span-12">
                                        <label
                                            htmlFor="phone_company"
                                            className="block text-sm font-medium text-gray-700"
                                        >
                                            Phone
                                        </label>
                                        <input
                                            required
                                            minLength={1}
                                            maxLength={280}
                                            type="text"
                                            name="phone_company"
                                            id="phone_company"
                                            value={phone}
                                            onChange={({ target }) => setPhone(target?.value)}
                                            //autoComplete="family-name"
                                            className="mt-1 block w-8/12 rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                        />
                                    </div>

                                    <div className="col-span-12">
                                        <label
                                            htmlFor="password"
                                            className="block text-sm font-medium text-gray-700"
                                        >
                                            Password
                                        </label>
                                        <input
                                            required
                                            minLength={1}
                                            maxLength={280}
                                            type="text"
                                            name="password"
                                            id="password"
                                            value={password}
                                            onChange={({ target }) => setPassword(target?.value)}
                                            //autoComplete="family-name"
                                            className="mt-1 block w-8/12 rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                        />
                                    </div>

                                    <div className="col-span-12">
                                        <label
                                            htmlFor="confirm_password"
                                            className="block text-sm font-medium text-gray-700"
                                        >
                                            Confirm Password
                                        </label>
                                        <input
                                            required
                                            minLength={1}
                                            maxLength={280}
                                            type="text"
                                            name="confirm_password"
                                            id="confirm_password"
                                            //autoComplete="family-name"
                                            value={confirmPassword}
                                            onChange={({ target }) => setConfirmPassword(target?.value)}
                                            className="mt-1 block w-8/12 rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                        />
                                    </div>
                                </div>
                                <div className="px-4 py-4">
                                    <button
                                        type="submit"
                                        onClick={handleSignUp}
                                        className="inline-flex w-3/5 justify-center rounded-md border border-transparent bg-green-600 py-2 px-4 text-sm font-medium text-white"
                                    >
                                        Sign Up
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default RegisterDonor;