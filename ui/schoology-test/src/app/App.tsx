import React, { useState } from 'react';
import './App.scss';

import Autocomplete from '../architecture/components/autocomplete';
import { Country } from './country/Country';
import { CountryAutoCompleteService } from './country/CountryAutoCompleteService';

const App: React.FC = () => {
  const countryACServiceName = new CountryAutoCompleteService();

  const countryACServiceAbbreviation = new CountryAutoCompleteService();
  countryACServiceAbbreviation.fieldName = 'abbreviation';
  countryACServiceAbbreviation.fieldValue = 'name';

  const [entity, setEntity] = useState(new Country());

  return (
    <div className="container">
      <h2>Schoology test - Autocomplete</h2>

      
      <Autocomplete
        label="Teste AC Name"
        service={countryACServiceName}
        value={entity.name}
        onChange={(e: any) => setEntity({ ...entity, name: e.target.value })}
      />

      <Autocomplete
        label="Teste AC Abbreviation"
        service={countryACServiceAbbreviation}
        value={entity.abbreviation}
        onChange={(e: any) => setEntity({ ...entity, abbreviation: e.target.value })}
      />

      <hr />
      <br />
      <label>Name </label>
      <span>{entity.name}</span>
      <br />

      <label>Abbreviation </label>
      <span>{entity.abbreviation}</span>
      
    </div>
  );
};

export default App;
