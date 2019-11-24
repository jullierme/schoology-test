import React, { useState } from 'react';
import './App.scss';

import Autocomplete from '../architecture/components/autocomplete/AutoComplete';
import { Country } from './country/Country';
import { CountryAutoCompleteService } from './country/CountryAutoCompleteService';

const App: React.FC = () => {
    const countryACServiceName = new CountryAutoCompleteService();

    const countryACServiceAbbreviation = new CountryAutoCompleteService();
    countryACServiceAbbreviation.fieldName = 'abbreviation';
    countryACServiceAbbreviation.fieldValue = 'name';

    const [dummy, setDummy] = useState(new Country());

    return (
        <div className="container">
            <h2>Schoology test - Autocomplete</h2>

            <label>Name as label and Abbreviation as code</label>
            <Autocomplete
                service={countryACServiceName}
                value={dummy.name}
                onChange={(e: any) => setDummy({ ...dummy, name: e.value })}
            />
            <span>Code: {dummy.name}</span>

            <br />

            <label>Abbreviation as label and Name as code</label>
            <Autocomplete
                service={countryACServiceAbbreviation}
                value={dummy.abbreviation}
                onChange={(e: any) =>
                    setDummy({ ...dummy, abbreviation: e.value })
                }
            />
            <span>Code: {dummy.abbreviation}</span>
        </div>
    );
};

export default App;
