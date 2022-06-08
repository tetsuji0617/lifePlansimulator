import React, { useState } from 'react'
import TextField from '@mui/material/TextField'

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
          <>

            <TextField
                id="input-year"
                label="Birth Year"
                valiant="outlined"
                inputProps={year}
                onChange={(e) => {
                    props.setValue({ ...props.value, [props.title]: e.target.value })
                    setYear(e.target.value)
                }}
                onBlur={handleBlur}
                margin="normal"
            />
          </>
    )
};

export default ValueInputYear;


