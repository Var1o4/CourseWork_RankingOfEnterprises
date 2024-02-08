package com.example.demo4.User;

public class SolveResult {
    final String groupA= "Группа A. Сумма цен интервалов 21-25. Предприятие имеет высокую рентабельность, оно финансово устойчиво. Его платежеспособность не вызывает сомнений. Качество финансового и производственного менеджмента высокое. Предприятие имеет отличные шансы для дальнейшего развития.";
    final String groupB = "Группа B. Сумма цен интервалов 11-20. Предприятие имеет удовлетворительный уровень рентабельности. Оно в целом платежеспособно и финансово устойчиво, хотя отдельные показатели ниже рекомендуемых значений. Однако данное предприятие недостаточно устойчиво к колебаниям рыночного спроса на продукцию и другим рыночным факторам. Работа с предприятием требует взвешенного подхода.";
    final String groupС = "Группа C. Сумма цен интервалов 4-10. Предприятие финансово неустойчиво, оно имеет низкую рентабельность для поддержания платежеспособности на приемлемом уровне. Как правило, такое предприятие имеет просроченную задолженность. Оно находится на грани потери финансовой устойчивости. Для выведения предприятия из кризиса следует предпринять значительные изменения в его финансово-хозяйственной деятельности. Инвестиции в предприятие связаны с повышенным риском.";
    final String groupD = "Группа D. Сумма цен интервалов < 4. Предприятие находится в глубоком финансовом кризисе. Размер кредиторской задолженности велик, оно не в состоянии расплатиться по своим обязательствам. Финансовая устойчивость предприятия практически полностью утрачена. Значение показателя рентабельности собственного капитала не позволяет надеяться на улучшение. Степень кризиса предприятия столь глубока, что вероятность улучшения ситуации даже в случае коренного изменения финансово-хозяйственной деятельности невысока.";

    private int totalScore =0;

    public void roeSolve(double roe, double cb){
        double del= roe/cb;
        if(del>((1/3)*cb/100)){
            totalScore+=5;
        }else if(((1/3)*cb/100)>=del && del>((1/4)*cb/100)){
            totalScore+=3;
        }else if(((1/4)*cb/100)>=del && del>0){
            totalScore+=1;
        }
    }
    public void equitySolve(double equity){
        if(equity>0.70){
            totalScore+=5;
        }else if(0.70>=equity && equity>0.60){
            totalScore+=3;
        }else if(0.60>=equity && equity>0.50){
            totalScore+=1;
        }
    }

    public void coverateSolve(double equity){
        if(equity>1.1){
            totalScore+=5;
        }else if(1.1>=equity && equity>1.0){
            totalScore+=3;
        }else if(1.0>=equity && equity>0.8){
            totalScore+=1;
        }
    }
    public void dpoSolve(double equity){
        if(equity<60){
            totalScore+=5;
        }else if(60<=equity && equity<90){
            totalScore+=3;
        }else if(90<=equity && equity<180){
            totalScore+=1;
        }
    }
    public void dpocSolve(double equity){
        if(equity<30){
            totalScore+=5;
        }else if(30<=equity && equity<60){
            totalScore+=3;
        }else if(60<=equity && equity<90){
            totalScore+=1;
        }
    }

    public String resultShow(){
        if(21<=totalScore && totalScore<=25){
            return "Баллов:"+ totalScore+". " +groupA;
        }else if(11<=totalScore && totalScore<=20){
            return "Баллов:"+ totalScore+". " +groupB;
        }else if(4<=totalScore && totalScore<=10){
            return "Баллов:"+ totalScore+". " +groupС;
        } else {
            return "Баллов:"+ totalScore+". " +groupD;
        }

    }

    public String roeScoreSolve(double roe, double cb) {
        int score = 0;
        double del = roe / cb;
        if (del > ((1.0 / 3.0) * cb)) {
            score += 5;
        } else if (((1.0 / 3.0) * cb) >= del && del > ((1.0 / 4.0) * cb)) {
            score += 3;
        } else if (((1.0 / 4.0) * cb) >= del && del > 0) {
            score += 1;
        }

        return "Баллы: " + score;
    }

    public String equityScoreSolve(double equity) {
        int score = 0;
        if (equity > 0.70) {
            score += 5;
        } else if (0.70 >= equity && equity > 0.60) {
            score += 3;
        } else if (0.60 >= equity && equity > 0.50) {
            score += 1;
        }

        return "Баллы: " + score;
    }

    public String coverateScoreSolve(double equity) {
        int score = 0;
        if (equity > 1.1) {
            score += 5;
        } else if (1.1 >= equity && equity > 1.0) {
            score += 3;
        } else if (1.0 >= equity && equity > 0.8) {
            score += 1;
        }

        return "Баллы: " + score;
    }

    public String dpoScoreSolve(double equity) {
        int score = 0;
        if (equity < 60) {
            score += 5;
        } else if (60 <= equity && equity < 90) {
            score += 3;
        } else if (90 <= equity && equity < 180) {
            score += 1;
        }

        return "Баллы: " + score;
    }

    public String dpocScoreSolve(double equity) {
        int score = 0;
        if (equity < 30) {
            score += 5;
        } else if (30 <= equity && equity < 60) {
            score += 3;
        } else if (60 <= equity && equity < 90) {
            score += 1;
        }

        return "Баллы: " + score;
    }

}
