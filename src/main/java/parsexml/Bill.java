package parsexml;

public class Bill {

	/*"update_time": "2015-08-25 19:04:03",
    "total_amt": 136,
    "bill_cycle": "2015-04-01 00:00:00",
    "pay_amt": null,
    "plan_amt": 76,
    "cell_phone": "156****9149"*/
	
	private String update_time = "";
	private String total_amt = "";
	private String bill_cycle = "";
	private String pay_amt = "";
	private String plan_amt = "";
	private String cell_phone = "";
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}
	public String getBill_cycle() {
		return bill_cycle;
	}
	public void setBill_cycle(String bill_cycle) {
		this.bill_cycle = bill_cycle;
	}
	public String getPay_amt() {
		return pay_amt;
	}
	public void setPay_amt(String pay_amt) {
		this.pay_amt = pay_amt;
	}
	public String getPlan_amt() {
		return plan_amt;
	}
	public void setPlan_amt(String plan_amt) {
		this.plan_amt = plan_amt;
	}
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	
}
