package com.example.myapplication;

import java.util.List;

/**
 * Created by Agata on 2016-11-12.
 */
public class Medicament {

    private Long id;
    private String name, hour, meal;

    // poszczególne gettery i settery (pozwalają na dostęp do prywatnych pól klasy Medicament);
    // UWAGA: aby wygenerować gettery i settery - prawy przycisk mysz -> generate -> getter and
    // setter -> zaznaczyć wszystkie zmienne i po sprawie

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }


    // find medicament by id

    public static Medicament findMedicamentById (int medId, MedsDatabase medi) {
        List<Medicament> meds = medi.getAllMeds();
        int i = 0;
        Medicament searchedMedicament = null;
        for (Medicament m : meds) {
            Long id = m.getId();
            if (id == medId) {
                searchedMedicament = m;
            }
        }
        return searchedMedicament;
    }

}
