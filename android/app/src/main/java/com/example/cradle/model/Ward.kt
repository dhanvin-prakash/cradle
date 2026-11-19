package com.example.cradle.model

enum class Ward(
    val displayName: String,
    val shortName: String,
    val parentHours: String,
    val visitorHours: String,
    val visitorsAtBedside: Int,
) {
    NeonatalIntensiveCare(
        displayName = "Neonatal Intensive Care Unit",
        shortName = "NICU",
        parentHours = "Open 24 hours",
        visitorHours = "12pm – 2pm, 5pm – 8pm",
        visitorsAtBedside = 2,
    ),
    SpecialCareNursery(
        displayName = "Special Care Nursery",
        shortName = "Special Care",
        parentHours = "Open 24 hours",
        visitorHours = "12pm – 8pm",
        visitorsAtBedside = 3,
    ),
    MaternityNursery(
        displayName = "Maternity Nursery",
        shortName = "Nursery",
        parentHours = "8am – 10pm",
        visitorHours = "10am – 8pm",
        visitorsAtBedside = 3,
    );

    /** Sample number only – replace with each ward's real line. */
    val phoneNumber: String get() = "tel:+6562255554"
}
