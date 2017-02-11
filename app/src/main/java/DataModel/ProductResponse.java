package DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lakshmisagar on 1/28/2017.
 */

public class ProductResponse {
    @SerializedName("originalTerm")
    private String _originalTerm;
    @SerializedName("currentResultCount")
    private Integer _currentResultCount;
    @SerializedName("totalResultCount")
    private Integer _totalResultCount;
    @SerializedName("term")
    private String _term;
    @SerializedName("results")
    private List<ProductModel> _results;
    @SerializedName("statusCode")
    private Integer _statusCode;

    public ProductResponse(Integer _totalResultCount, String _originalTerm, Integer _currentResultCount, String _term, List<ProductModel> _results, Integer _statusCode) {
        this._totalResultCount = _totalResultCount;
        this._originalTerm = _originalTerm;
        this._currentResultCount = _currentResultCount;
        this._term = _term;
        this._results = _results;
        this._statusCode = _statusCode;
    }

    public String get_originalTerm() {
        return _originalTerm;
    }

    public void set_originalTerm(String _originalTerm) {
        this._originalTerm = _originalTerm;
    }

    public Integer get_currentResultCount() {
        return _currentResultCount;
    }

    public void set_currentResultCount(Integer _currentResultCount) {
        this._currentResultCount = _currentResultCount;
    }

    public Integer get_totalResultCount() {
        return _totalResultCount;
    }

    public void set_totalResultCount(Integer _totalResultCount) {
        this._totalResultCount = _totalResultCount;
    }

    public String get_term() {
        return _term;
    }

    public void set_term(String _term) {
        this._term = _term;
    }

    public List<ProductModel> get_results() {
        return _results;
    }

    public void set_results(List<ProductModel> _results) {
        this._results = _results;
    }

    public Integer get_statusCode() {
        return _statusCode;
    }

    public void set_statusCode(Integer _statusCode) {
        this._statusCode = _statusCode;
    }
}

