import { CountryAutoCompleteService } from "../../../app/country/CountryAutoCompleteService";
import { AutoCompleteServiceImpl } from "../../../architecture/components/autocomplete/AutoCompleteService";


it('CountryAutoCompleteService should implements the correct interface', () => {
    const country = new CountryAutoCompleteService();

    expect(country).toBeInstanceOf(AutoCompleteServiceImpl);
});

it('CountryAutoCompleteService should have default parameters', () => {
    //given
    const api = '/country';
    const fieldName = 'name';
    const fieldValue = 'abbreviation';

    //when
    const country = new CountryAutoCompleteService();

    //then
    expect(country.api).toEqual(api);
    expect(country.fieldName).toEqual(fieldName);
    expect(country.fieldValue).toEqual(fieldValue);
});

