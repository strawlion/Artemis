import * as formatUtils from '../../utils/format-utils';

export class PersonView {
    persons = formatUtils.getMockPersons();

    toObservationDisplayValue(observation) {
        return observation.date.format('MMMM Do, YYYY');
    }

    toObservationPeriodDisplayValue(observationPeriod) {
        return observationPeriod.startDate.format('MMMM Do, YYYY') + ' - ' + observationPeriod.endDate.format('MMMM Do, YYYY')
    }
}
