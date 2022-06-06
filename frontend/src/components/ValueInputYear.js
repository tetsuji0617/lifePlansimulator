import React, { useState } from 'react'

const ValueInputYear = (props) => {
    const [year, setYear] = useState('')
    const [yearError, setYearError] = useState('')

    const handleBlur = (e) => {
        const year = e.target.value
        if (!year) {
            setYearError('required')
        } else if (year.length < 4) {
            setYearError('should longer than 4')
        } else {
            setYearError()
        }
    }

    return (
        <div>
            <label>{props.title}:
                <input type='text'
                    value={year}
                    onChange={(e) => {
                        props.setValue({...e.target.value, [props.title]: e.target.value})
                        console.log('valueinputYear:' + e.target.value + ' ' + e.target.title)
                        setYear(e.target.value)
                    }}
                    onBlur={handleBlur}
                />
                {yearError && <label>{yearError}</label>}
            </label>

        </div>
    )
};

export default ValueInputYear;