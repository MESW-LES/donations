import { Listbox, Transition } from "@headlessui/react";
import axios from "axios";
import { Fragment, useState } from "react";
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'

const categorylist = [
    {
        name: 'Test Category 1',
    },
    {
        name: 'Test Category 2',
    },
]

function classNames(...classes: string[]) {
    return classes.filter(Boolean).join(' ')
}

function RegisterDonee() {
    const [taxnumber, setTaxnumber] = useState("");
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");
    const [category, setCategory] = useState(categorylist[1])


    const handleSignUp = async (event: any) => {
        //não atualiza a pagina
        event.preventDefault();

        const formValues = { name, description, taxnumber, phone, password, category };

        console.log(formValues);

        try {
            const { data } = await axios({
                url: "/api/formDonee",
                method: "POST",
                data: formValues,
            });

            console.log("Response: ", data);
        } catch (error) {
            console.log("error:", error);
        }
    };

    const callAPI = async () => {
        try {
            const res = await fetch(
                `https://jsonplaceholder.typicode.com/posts/1`
            );
            const data = await res.json();
            console.log(data.userId);
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <>
            <div className="bg-slate-800 bg-cover bg-center mt-20 sm:mt-0 pt-20">
                <form action="/api/formDonee" method="POST">
                    <div className="overflow-hidden shadow sm:rounded-md space-y-4 px-24">
                        <div className="border border-transparent rounded-md bg-white py-5 sm:p-6">
                            <h2 className="text-lg font-bold text-center mb-4">Sign up</h2>
                            <div className="grid grid-cols-12 gap-4 content-center">
                                <div className="col-start-4 col-span-6">
                                    <label
                                        htmlFor="taxnumber"
                                        className="block text-sm font-medium text-gray-700"
                                    >
                                        Company Number NIPC
                                    </label>
                                    <input
                                        required
                                        minLength={1}
                                        maxLength={4}
                                        type="text"
                                        name="taxnumber"
                                        id="taxnumber"
                                        value={taxnumber}
                                        //autoComplete="given-name"
                                        onChange={({ target }) => setTaxnumber(target?.value)}
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>
                                <div className="col-start-4 col-span-6">
                                    <label
                                        htmlFor="taxnumber"
                                        className="block text-sm font-medium text-gray-700"
                                    >
                                        Description
                                    </label>
                                    <input
                                        required
                                        minLength={1}
                                        maxLength={4}
                                        type="text"
                                        name="taxnumber"
                                        id="taxnumber"
                                        value={description}
                                        //autoComplete="given-name"
                                        onChange={({ target }) => setDescription(target?.value)}
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>
                                <div className="col-start-4 col-span-6">
                                    <label
                                        htmlFor="company_name"
                                        className="block text-sm font-medium text-gray-700"
                                    >
                                        Company Name
                                    </label>
                                    <input
                                        required
                                        minLength={1}
                                        maxLength={50}
                                        type="text"
                                        name="company_name"
                                        id="company_name"
                                        value={name}
                                        onChange={({ target }) => setName(target?.value)}
                                        //autoComplete="given-name"
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>

                                <div className="col-start-4 col-span-6">
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
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>

                                <div className="col-start-4 col-span-6">
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
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>

                                <div className="col-start-4 col-span-6">
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
                                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:outline-none focus:ring-indigo-500 sm:text-sm"
                                    />
                                </div>

                                <div className="col-start-4 col-span-6">
                                    <Listbox value={category} onChange={setCategory}>
                                        {({ open }) => (
                                            <>
                                                <Listbox.Label className="block text-sm font-medium text-gray-700">Category</Listbox.Label>
                                                <div className="relative mt-1 ">
                                                    <Listbox.Button className="relative w-full cursor-default rounded-md border border-gray-300 bg-white py-2 pl-3 pr-10 text-left shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm">
                                                        <span className="flex items-center">
                                                            <span className="ml-3 block truncate">{category.name}</span>
                                                        </span>
                                                        <span className="pointer-events-none absolute inset-y-0 right-0 ml-3 flex items-center pr-2">
                                                            <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
                                                        </span>
                                                    </Listbox.Button>

                                                    <Transition
                                                        show={open}
                                                        as={Fragment}
                                                        leave="transition ease-in duration-100"
                                                        leaveFrom="opacity-100"
                                                        leaveTo="opacity-0"
                                                    >
                                                        <Listbox.Options className="absolute z-10 mt-1 max-h-56 w-8/12 overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                                                            {categorylist.map((categorylist) => (
                                                                <Listbox.Option
                                                                    key={categorylist.name}
                                                                    className={({ active }) =>
                                                                        classNames(
                                                                            active ? 'text-white bg-indigo-600' : 'text-gray-900',
                                                                            'relative cursor-default select-none py-2 pl-3 pr-9'
                                                                        )
                                                                    }
                                                                    value={categorylist}
                                                                >
                                                                    {({ selected, active }) => (
                                                                        <>
                                                                            <div className="flex items-center">
                                                                                <span
                                                                                    className={classNames(selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate')}
                                                                                >
                                                                                    {categorylist.name}
                                                                                </span>
                                                                            </div>

                                                                            {selected ? (
                                                                                <span
                                                                                    className={classNames(
                                                                                        active ? 'text-white' : 'text-indigo-600',
                                                                                        'absolute inset-y-0 right-0 flex items-center pr-4'
                                                                                    )}
                                                                                >
                                                                                    <CheckIcon className="h-5 w-5" aria-hidden="true" />
                                                                                </span>
                                                                            ) : null}
                                                                        </>
                                                                    )}
                                                                </Listbox.Option>
                                                            ))}
                                                        </Listbox.Options>
                                                    </Transition>
                                                </div>
                                            </>
                                        )}
                                    </Listbox>
                                </div>
                                <div className="col-start-4 col-span-6">
                                    <button
                                        type="submit"
                                        onClick={handleSignUp}
                                        className="inline-flex w-3/5 justify-center rounded-md border border-transparent bg-green-600 py-2 px-4 text-sm font-medium text-white"
                                    >
                                        Sign Up
                                    </button>
                                </div>
                                <div className="col-start-4 col-span-6">

                                    <button onClick={callAPI}>Make API Call</button>

                                </div>
                            </div>

                        </div>

                    </div>
                </form>
            </div>
        </>
    );
}

export default RegisterDonee;