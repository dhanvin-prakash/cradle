package com.example.cradle.data

import com.example.cradle.model.Baby
import com.example.cradle.model.CareStatus
import com.example.cradle.model.CareUpdate
import com.example.cradle.model.FeedingInfo
import com.example.cradle.model.UpdateKind
import com.example.cradle.model.Vital
import com.example.cradle.model.VitalKind
import com.example.cradle.model.Ward
import java.time.Duration
import java.time.Instant

/** Demo twins so the baby switcher and both ward types can be explored. */
object SampleData {
    fun babies(now: Instant = Instant.now()): List<Baby> {
        val birth = now.minus(Duration.ofDays(4)).minus(Duration.ofHours(3))
        fun ago(minutes: Long): Instant = now.minus(Duration.ofMinutes(minutes))
        fun inMinutes(minutes: Long): Instant = now.plus(Duration.ofMinutes(minutes))

        val chloe = Baby(
            id = "chloe",
            givenName = "Chloe",
            familyName = "Tan",
            birthDate = birth,
            birthWeightInKilograms = 2.41,
            ward = Ward.SpecialCareNursery,
            bed = "SCN 12",
            status = CareStatus.Stable,
            vitals = listOf(
                Vital(VitalKind.HeartRate, 138.0),
                Vital(VitalKind.Oxygen, 98.0),
                Vital(VitalKind.Breathing, 44.0),
                Vital(VitalKind.Temperature, 36.8),
                Vital(VitalKind.Weight, 2.36),
            ),
            feeding = FeedingInfo(
                lastFeed = ago(75),
                nextFeed = inMinutes(105),
                method = "Expressed breast milk by bottle",
                amountInMillilitres = 40,
            ),
            nurse = "Siti Rahman",
            doctor = "Wei Ming Lim",
            nurseNote = "Chloe finished her whole feed and loved her skin-to-skin cuddle this morning. She's a little star!",
            updates = listOf(
                CareUpdate(date = ago(75), kind = UpdateKind.Feed, title = "Finished a full feed", detail = "40 ml of expressed breast milk. Burped well afterwards.", author = "Nurse Siti"),
                CareUpdate(date = ago(80), kind = UpdateKind.Nappy, title = "Nappy change", detail = "Wet and dirty nappy – all normal.", author = "Nurse Siti"),
                CareUpdate(date = ago(210), kind = UpdateKind.Cuddle, title = "Skin-to-skin with Mum", detail = "45 minutes of kangaroo care. Heart rate stayed lovely and steady.", author = "Nurse Siti"),
                CareUpdate(date = ago(300), kind = UpdateKind.DoctorRound, title = "Morning ward round", detail = "Dr Lim is happy with Chloe's progress. Plan: keep building feeds.", author = "Dr Lim"),
                CareUpdate(date = ago(1_300), kind = UpdateKind.Weight, title = "Weighed tonight", detail = "2.36 kg – a normal small dip from her birth weight of 2.41 kg.", author = "Nurse Grace"),
                CareUpdate(date = ago(1_420), kind = UpdateKind.NurseNote, title = "Settled night", detail = "Slept well between feeds and was easy to settle.", author = "Nurse Grace"),
            ),
            lastUpdated = ago(6),
        )

        val ethan = Baby(
            id = "ethan",
            givenName = "Ethan",
            familyName = "Tan",
            birthDate = birth,
            birthWeightInKilograms = 1.98,
            ward = Ward.NeonatalIntensiveCare,
            bed = "NICU 4",
            status = CareStatus.CloselyMonitored,
            vitals = listOf(
                Vital(VitalKind.HeartRate, 152.0),
                Vital(VitalKind.Oxygen, 94.0),
                Vital(VitalKind.Breathing, 58.0),
                Vital(VitalKind.Temperature, 36.6),
                Vital(VitalKind.Weight, 1.93),
            ),
            feeding = FeedingInfo(
                lastFeed = ago(50),
                nextFeed = inMinutes(130),
                method = "Tube feed of breast milk",
                amountInMillilitres = 22,
            ),
            nurse = "Priya Nair",
            doctor = "Hui Min Goh",
            nurseNote = "Ethan is having some gentle breathing support to help him rest. He's comfortable and grabbed my finger today!",
            updates = listOf(
                CareUpdate(date = ago(50), kind = UpdateKind.Feed, title = "Tube feed", detail = "22 ml of breast milk given slowly. Tolerated well.", author = "Nurse Priya"),
                CareUpdate(date = ago(140), kind = UpdateKind.NurseNote, title = "Breathing support", detail = "Ethan is on a little extra air flow through his nose. This is very common for twins born early.", author = "Nurse Priya"),
                CareUpdate(date = ago(290), kind = UpdateKind.DoctorRound, title = "Morning ward round", detail = "Dr Goh says Ethan is stable. We'll review his breathing support tomorrow.", author = "Dr Goh"),
                CareUpdate(date = ago(1_350), kind = UpdateKind.Weight, title = "Weighed tonight", detail = "1.93 kg – a small, expected dip.", author = "Nurse Grace"),
                CareUpdate(date = ago(1_500), kind = UpdateKind.Cuddle, title = "Hand hug with Dad", detail = "Dad held Ethan's hand through the incubator for 20 minutes.", author = "Nurse Grace"),
            ),
            lastUpdated = ago(3),
        )

        return listOf(chloe, ethan)
    }
}
