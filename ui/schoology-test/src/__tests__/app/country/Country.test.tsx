import { Country } from "../../../app/country/Country";


it('Country should have correct properties', () => {
    const country = new Country();

    expect(country).toHaveProperty('name');
    expect(country).toHaveProperty('abbreviation');
});

it('Should create an object with properties', () => {
    //given
    const name = 'xyz';
    const abbreviation = 'abc';

    //when
    const country = new Country();
    country.name = name;
    country.abbreviation = abbreviation;

    //then
    expect(country.name).toEqual(name);
    expect(country.abbreviation).toEqual(abbreviation);
});
