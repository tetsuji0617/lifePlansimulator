import React, { useState } from 'react'




const ValueInput = (props) => {
    const [input, setInput] = useState(props.value)
    const [inputError, setInputError] = useState('')


    const handleBlur = (e) => {
        console.log('valueinput.handleBlur')
        const v = e.target.value
        const newValue = v.replace(/[^0-9.]+/g, '')
        if (!newValue) {
            setInputError('required')
        } else {
            setInputError()
        }
    }


    return (
        <div>
            <label>{props.title}: <br />
                <input type='text'
                    value={input}
                    onChange={(e) =>{
                        props.setValue(e.target.value)
                        setInput(e.target.value)
                    }}
                    onBlur={handleBlur} />
            </label>
            {inputError && <label>{inputError}</label>}
        </div>
    )
};

export default ValueInput;