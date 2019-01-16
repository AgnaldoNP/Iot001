package pereira.agnaldo.iot001.activity

import android.annotation.SuppressLint
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.database.entity.OP
import pereira.agnaldo.iot001.database.entity.OPCard
import pereira.agnaldo.iot001.database.entity.OPCardListItem
import pereira.agnaldo.iot001.database.entity.User
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread

@SuppressLint("Registered")
@EActivity(R.layout.activity_splash)
open class SplashActivity : BaseActivity() {

    @AfterViews
    protected open fun afterViews() {
        mockupDatabase()
        checkNextActivity()
    }

    @Background
    protected open fun mockupDatabase() {
        val userImage =
            "https://lh3.googleusercontent.com/-2y3RPHEhNr0/VjqO0_V2hDI/AAAAAAAAGco/pdm1mcfpA04/w530-h530-n/580013_255338187903127_2115924358_n.jpg"
        val user = User(1, "Agnaldo N. Pereira", userImage, "agnaldo.pereira@etec.sp.gov.br", "2y3RPHEhNr0")
        IoT001Database.getInstance(this).daoUser().insert(user)


        val op = OP(1, "OP 001", "Canetas")
        IoT001Database.getInstance(this).daoOP().insert(op)

        val opCard1 = OPCard(1, op, 75f, "Minutos")
        opCard1.opcardItems.add(OPCardListItem(1, opCard1, "2018-12-13 09:00:00.000", 15, "00"))
        opCard1.opcardItems.add(OPCardListItem(2, opCard1, "2018-12-13 09:01:00.000", 16, "01"))
        opCard1.opcardItems.add(OPCardListItem(3, opCard1, "2018-12-13 09:02:00.000", 17, "02"))
        opCard1.opcardItems.add(OPCardListItem(4, opCard1, "2018-12-13 09:03:00.000", 18, "03"))
        opCard1.opcardItems.add(OPCardListItem(5, opCard1, "2018-12-13 09:04:00.000", 15, "04"))
        opCard1.opcardItems.add(OPCardListItem(6, opCard1, "2018-12-13 09:05:00.000", 14, "05"))
        opCard1.opcardItems.add(OPCardListItem(7, opCard1, "2018-12-13 09:06:00.000", 13, "06"))
        opCard1.opcardItems.add(OPCardListItem(8, opCard1, "2018-12-13 09:07:00.000", 20, "07"))

        opCard1.opcardItems.add(OPCardListItem(9, opCard1, "2018-12-13 10:01:00.000", 20,  "01"))
        opCard1.opcardItems.add(OPCardListItem(10, opCard1, "2018-12-13 10:02:00.000", 18,  "02"))
        opCard1.opcardItems.add(OPCardListItem(11, opCard1, "2018-12-13 10:03:00.000", 19,  "03"))
        opCard1.opcardItems.add(OPCardListItem(12, opCard1, "2018-12-13 10:04:00.000", 17,  "04"))
        opCard1.opcardItems.add(OPCardListItem(13, opCard1, "2018-12-13 10:05:00.000", 17,  "05"))
        opCard1.opcardItems.add(OPCardListItem(14, opCard1, "2018-12-13 10:06:00.000", 15,  "06"))
        opCard1.opcardItems.add(OPCardListItem(15, opCard1, "2018-12-13 10:07:00.000", 18,  "07"))
        opCard1.opcardItems.add(OPCardListItem(16, opCard1, "2018-12-13 10:08:00.000", 17,  "08"))
        opCard1.opcardItems.add(OPCardListItem(17, opCard1, "2018-12-13 10:09:00.000", 16,  "09"))
        opCard1.opcardItems.add(OPCardListItem(18, opCard1, "2018-12-13 10:10:00.000", 15,  "10"))
        opCard1.opcardItems.add(OPCardListItem(19, opCard1, "2018-12-13 10:11:00.000", 20,  "11"))
        opCard1.opcardItems.add(OPCardListItem(20, opCard1, "2018-12-13 10:12:00.000", 19,  "12"))
        opCard1.opcardItems.add(OPCardListItem(21, opCard1, "2018-12-13 10:13:00.000", 18,  "13"))
        opCard1.opcardItems.add(OPCardListItem(22, opCard1, "2018-12-13 10:14:00.000", 18,  "14"))
        opCard1.opcardItems.add(OPCardListItem(23, opCard1, "2018-12-13 10:15:00.000", 18,  "15"))

        opCard1.opcardItems.add(OPCardListItem(20, opCard1, "2018-12-14 10:00:00.000", 20, "00"))
        opCard1.opcardItems.add(OPCardListItem(21, opCard1, "2018-12-14 10:01:00.000", 20, "01"))
        opCard1.opcardItems.add(OPCardListItem(22, opCard1, "2018-12-14 10:02:00.000", 20, "02"))
        opCard1.opcardItems.add(OPCardListItem(23, opCard1, "2018-12-14 10:03:00.000", 19, "03"))
        opCard1.opcardItems.add(OPCardListItem(24, opCard1, "2018-12-14 10:04:00.000", 19, "04"))
        opCard1.opcardItems.add(OPCardListItem(25, opCard1, "2018-12-14 10:05:00.000", 18, "05"))
        opCard1.opcardItems.add(OPCardListItem(26, opCard1, "2018-12-14 10:06:00.000", 20, "06"))

        IoT001Database.getInstance(this).daoOPCard().insertAll(this, opCard1)


        val op2 = OP(2, "OP 002", "Borrachas")
        IoT001Database.getInstance(this).daoOP().insert(op2)

        val opCard2 = OPCard(2, op2, 38f, "Hora")
        opCard2.opcardItems.add(OPCardListItem(27, opCard2, "2018-12-13 06:00:00.000", 150, "06"))
        opCard2.opcardItems.add(OPCardListItem(28, opCard2, "2018-12-13 07:00:00.000", 135, "07"))
        opCard2.opcardItems.add(OPCardListItem(29, opCard2, "2018-12-13 08:00:00.000", 160, "08"))
        opCard2.opcardItems.add(OPCardListItem(30, opCard2, "2018-12-13 09:00:00.000", 165, "09"))
        opCard2.opcardItems.add(OPCardListItem(31, opCard2, "2018-12-13 10:00:00.000", 140, "10"))
        opCard2.opcardItems.add(OPCardListItem(32, opCard2, "2018-12-13 11:00:00.000", 120, "11"))
        IoT001Database.getInstance(this).daoOPCard().insertAll(this, opCard2)

//        val allFilled = IoT001Database.getInstance(this).daoOPCard().getAllFilled(this)
//        allFilled.toString()
    }

    @UiThread(delay = 1000)
    protected open fun checkNextActivity() {
        LoginActivity_.intent(this).start()
        finish()
    }

}
