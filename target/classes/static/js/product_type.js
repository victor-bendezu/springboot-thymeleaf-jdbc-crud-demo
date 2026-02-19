(function () {
  "use strict";

  const API = {
    classifications: "/api/product-type/classifications",
    list: "/api/product-type",
    save: "/api/product-type",
    delete: (id) => `/api/product-type/${id}`
  };

  let modal;

  $(document).ready(function () {
    modal = new bootstrap.Modal(document.getElementById("productTypeModal"), { backdrop: "static" });
    bindEvents();
    init();
  });

  function bindEvents() {
    $("#btnNew").off("click.pt").on("click.pt", function () {
      openModalForNew();
    });

    $("#btnRefresh").off("click.pt").on("click.pt", function () {
      loadTable();
    });

    $("#btnSave").off("click.pt").on("click.pt", function () {
      save();
    });

    $("#tbProductTypes")
      .off("click.pt", ".btn-edit")
      .on("click.pt", ".btn-edit", function () {
        const rowData = $(this).closest("tr").data("row");
        openModalForEdit(rowData);
      });

    $("#tbProductTypes")
      .off("click.pt", ".btn-delete")
      .on("click.pt", ".btn-delete", function () {
        const rowData = $(this).closest("tr").data("row");
        remove(rowData.idProductType, rowData.name);
      });
  }

  function init() {
    hideAlert();
    loadClassifications().then(() => loadTable());
  }

  function loadClassifications() {
    return $.ajax({
      url: API.classifications,
      type: "GET",
      success: function (response, textStatus, xhr) {
        if (xhr.status !== 200) {
          showAlert("danger", "Failed to load classifications.");
          return;
        }
        if (!response || response.code !== 200) {
          showAlert("danger", response?.message || "Failed to load classifications.");
          return;
        }
        renderClassifications(response.data || []);
      },
      error: function () {
        showAlert("danger", "Error calling classifications endpoint.");
      }
    });
  }

  function renderClassifications(list) {
    const $sel = $("#idClassification");
    $sel.empty();
    $sel.append(`<option value="">-- Select --</option>`);
    list.forEach((c) => {
      $sel.append(`<option value="${c.idClassification}">${escapeHtml(c.name)}</option>`);
    });
  }

  function loadTable() {
    hideAlert();
    $.ajax({
      url: API.list,
      type: "GET",
      success: function (response, textStatus, xhr) {
        if (xhr.status !== 200) {
          showAlert("danger", "Failed to load product types.");
          return;
        }
        if (!response || response.code !== 200) {
          showAlert("danger", response?.message || "Failed to load product types.");
          return;
        }
        renderTable(response.data || []);
      },
      error: function () {
        showAlert("danger", "Error calling list endpoint.");
      }
    });
  }

  function renderTable(list) {
    const $tb = $("#tbProductTypes");
    $tb.empty();

    if (!list.length) {
      $tb.append(`<tr><td colspan="6" class="text-center text-secondary py-4">No records found</td></tr>`);
      return;
    }

    list.forEach((x) => {
      const statusBadge = x.status === 1
        ? `<span class="badge text-bg-success">Active</span>`
        : `<span class="badge text-bg-secondary">Inactive</span>`;

      const $tr = $(`
        <tr>
          <td>${x.idProductType}</td>
          <td class="fw-semibold">${escapeHtml(x.code)}</td>
          <td>${escapeHtml(x.name)}</td>
          <td>${escapeHtml(x.classificationName || "")}</td>
          <td>${statusBadge}</td>
          <td class="text-end">
            <button class="btn btn-sm btn-outline-primary btn-edit">Edit</button>
            <button class="btn btn-sm btn-outline-danger btn-delete">Delete</button>
          </td>
        </tr>
      `);

      $tr.data("row", x);
      $tb.append($tr);
    });
  }

  function openModalForNew() {
    $("#modalTitle").text("New Product Type");
    $("#idProductType").val("");
    $("#code").val("");
    $("#name").val("");
    $("#idClassification").val("");
    $("#status").val("1");
    hideAlert();
    modal.show();
    setTimeout(() => $("#code").trigger("focus"), 200);
  }

  function openModalForEdit(row) {
    $("#modalTitle").text("Edit Product Type");
    $("#idProductType").val(row.idProductType);
    $("#code").val(row.code);
    $("#name").val(row.name);
    $("#idClassification").val(String(row.idClassification));
    $("#status").val(String(row.status));
    hideAlert();
    modal.show();
    setTimeout(() => $("#name").trigger("focus"), 200);
  }

  function save() {
    hideAlert();

    const payload = {
      idProductType: toIntOrNull($("#idProductType").val()),
      code: $("#code").val().trim(),
      name: $("#name").val().trim(),
      idClassification: toIntOrNull($("#idClassification").val()),
      status: toIntOrNull($("#status").val())
    };

    const validationMsg = validate(payload);
    if (validationMsg) {
      showAlert("warning", validationMsg);
      return;
    }

    $.ajax({
      url: API.save,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(payload),
      success: function (response, textStatus, xhr) {
        if (xhr.status !== 200) {
          showAlert("danger", "Save failed.");
          return;
        }
        if (!response || response.code !== 200) {
          showAlert("danger", response?.message || "Save failed.");
          return;
        }
        modal.hide();
        loadTable();
        showAlert("success", response.message || "Saved.");
      },
      error: function (xhr) {
        const msg = tryExtractMessage(xhr) || "Error calling save endpoint.";
        showAlert("danger", msg);
      }
    });
  }

  function remove(id, name) {
    hideAlert();
    if (!confirm(`Delete "${name}"?`)) return;

    $.ajax({
      url: API.delete(id),
      type: "DELETE",
      success: function (response, textStatus, xhr) {
        if (xhr.status !== 200) {
          showAlert("danger", "Delete failed.");
          return;
        }
        if (!response || response.code !== 200) {
          showAlert("danger", response?.message || "Delete failed.");
          return;
        }
        loadTable();
        showAlert("success", response.message || "Deleted.");
      },
      error: function (xhr) {
        const msg = tryExtractMessage(xhr) || "Error calling delete endpoint.";
        showAlert("danger", msg);
      }
    });
  }

  function validate(p) {
    if (!p.code) return "Code is required.";
    if (!p.name) return "Name is required.";
    if (!p.idClassification) return "Classification is required.";
    if (p.status === null || p.status === undefined) return "Status is required.";
    return "";
  }

  function showAlert(type, message) {
    const $a = $("#alertBox");
    $a.removeClass("d-none alert-success alert-danger alert-warning alert-info");
    $a.addClass(`alert-${type}`);
    $a.text(message);
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  function hideAlert() {
    const $a = $("#alertBox");
    $a.addClass("d-none");
    $a.removeClass("alert-success alert-danger alert-warning alert-info");
    $a.text("");
  }

  function toIntOrNull(v) {
    if (v === null || v === undefined) return null;
    const s = String(v).trim();
    if (!s) return null;
    const n = parseInt(s, 10);
    return Number.isNaN(n) ? null : n;
  }

  function tryExtractMessage(xhr) {
    try {
      if (xhr && xhr.responseText) {
        const obj = JSON.parse(xhr.responseText);
        return obj?.message || null;
      }
      return null;
    } catch (e) {
      return null;
    }
  }

  function escapeHtml(str) {
    return String(str)
      .replaceAll("&", "&amp;")
      .replaceAll("<", "&lt;")
      .replaceAll(">", "&gt;")
      .replaceAll('"', "&quot;")
      .replaceAll("'", "&#039;");
  }
})();
