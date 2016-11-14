
public enum Gender {

    MALE(8507L),
    FEMALE(8532L),
    UNKNOWN(null);

    private Long genderConceptId;

    Gender(Long genderConceptId) {
        this.genderConceptId = genderConceptId;
    }


    public Gender fromGenderConceptId(Long genderConceptId) {
        for (Gender gender : Gender.values()) {
            if (gender.equals(genderConceptId)) {
                return gender;
            }
        }

        return UNKNOWN;
    }




}
