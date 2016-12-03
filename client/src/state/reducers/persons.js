import moment from 'moment';

import { genderConceptIdToGender, ethnicityConceptIdToEthnicity, raceConceptIdToEthnicity } from '../../utils/enums';


export default function personsReducer(state = { isLoading: true, page: 0, value: [] }, action) {
    switch (action.type) {
        case 'PERSONS_CHANGED':
            return Object.assign({}, state, {
                isLoading: false,
                page: action.persons.length ? state.page + 1 : state.page,
                value: action.persons.map(toNormalizedPerson)
            });
        case 'PERSON_UPDATED':
            const newPerson = toFormattedPerson(action.person);
            return Object.assign({}, state, {
                value: state.value.slice().map(person => person.id === newPerson.id ? newPerson : person),
            });
        case 'FILTER_CHANGED':
            return Object.assign({}, state, {
                isLoading: true,
                page: 0,
                value: [],
            });
        default:
            return state;
    }
};



function toFormattedPerson(rawPerson) {
    return Object.assign(
        toNormalizedPerson(rawPerson.person),
        {
            observations: rawPerson.observations.map(toObservation).sort((a, b) => a.date.unix() - b.date.unix()),
            observationPeriods: rawPerson.observationPeriods.map(toObservationPeriod).sort((a, b) => a.startDate.unix() - b.startDate.unix()),
        }
    );
}

function toNormalizedPerson(rawPerson) {
    const gender = genderConceptIdToGender[rawPerson.gender_concept_id] || 'Unknown';
    return {
        id: rawPerson.person_id,
        name: `Anonymous ${gender} ${rawPerson.person_id}`,
        gender,
        isDead: !!rawPerson.death_date,
        dateOfDeath: rawPerson.death_date ? moment(rawPerson.death_date) : null,
        dateOfBirth: moment().year(rawPerson.year_of_birth)
                            .month(rawPerson.month_of_birth)
                            .day(rawPerson.day_of_birth),
        race: raceConceptIdToEthnicity[rawPerson.race_concept_id] || 'Unknown',
        ethnicity: ethnicityConceptIdToEthnicity[rawPerson.ethnicity_concept_id] || 'Unknown',
        location: rawPerson.location_id, // TODO:
    };
}


function toObservation(rawObservation) {
  return {
    id: rawObservation.observation_id,
    date: moment(rawObservation.observation_time),
    valueAsConceptId: rawObservation.value_as_concept_id,
    providerId: rawObservation.provider_id,
    visitOccurrenceId: rawObservation.visit_occurrence_id,
    observationSourceValue: rawObservation.observation_source_value,
    observationSourceConceptId: rawObservation.observation_source_concept_id,
  };
}

function toObservationPeriod(rawObservationPeriod) {
  return {
    id: rawObservationPeriod.observation_period_id,
    startDate: moment(rawObservationPeriod.observation_period_start_time),
    endDate: moment(rawObservationPeriod.observation_period_end_time),
  };
}